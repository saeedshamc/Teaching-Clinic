package main

import "fmt"

type Notifier interface {
	Send(message string) error
}

type EmailNotifier struct{}

func (EmailNotifier) Send(message string) error {
	fmt.Println("email:", message)
	return nil
}

type OrderService struct {
	notifier Notifier
}

func NewOrderService(n Notifier) *OrderService {
	return &OrderService{notifier: n}
}

func (s *OrderService) Place(id string) error {
	return s.notifier.Send("order " + id + " ثبت شد")
}

func main() {
	_ = NewOrderService(EmailNotifier{}).Place("A-42")
}
