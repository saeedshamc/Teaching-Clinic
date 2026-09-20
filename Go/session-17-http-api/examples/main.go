package main

import (
	"encoding/json"
	"log"
	"net/http"
	"sync"
	"time"
)

type Note struct {
	ID    string `json:"id"`
	Title string `json:"title"`
}

type Store struct {
	mu    sync.Mutex
	notes map[string]Note
}

func NewStore() *Store {
	return &Store{notes: map[string]Note{}}
}

func (s *Store) List() []Note {
	s.mu.Lock()
	defer s.mu.Unlock()
	out := make([]Note, 0, len(s.notes))
	for _, n := range s.notes {
		out = append(out, n)
	}
	return out
}

func (s *Store) Add(title string) Note {
	s.mu.Lock()
	defer s.mu.Unlock()
	n := Note{ID: time.Now().Format("150405.000"), Title: title}
	s.notes[n.ID] = n
	return n
}

func main() {
	store := NewStore()
	mux := http.NewServeMux()

	mux.HandleFunc("/notes", func(w http.ResponseWriter, r *http.Request) {
		switch r.Method {
		case http.MethodGet:
			writeJSON(w, http.StatusOK, store.List())
		case http.MethodPost:
			var body struct {
				Title string `json:"title"`
			}
			if err := json.NewDecoder(r.Body).Decode(&body); err != nil || body.Title == "" {
				writeJSON(w, http.StatusBadRequest, map[string]string{"error": "title required"})
				return
			}
			writeJSON(w, http.StatusCreated, store.Add(body.Title))
		default:
			http.Error(w, "method not allowed", http.StatusMethodNotAllowed)
		}
	})

	log.Println("notes api on :8080")
	log.Fatal(http.ListenAndServe(":8080", mux))
}

func writeJSON(w http.ResponseWriter, status int, v any) {
	w.Header().Set("Content-Type", "application/json; charset=utf-8")
	w.WriteHeader(status)
	_ = json.NewEncoder(w).Encode(v)
}
