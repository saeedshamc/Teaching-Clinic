# mini_project_notebook.py — مینی‌پروژهٔ «Notebook» (جلسه ۴۹)
# این فایل شامل همهٔ کدهای لازم برای پروژهٔ مرور است.
# آن را به فایل‌های جدا (models.py, views.py, tests.py) تقسیم کنید.

# ══════════════════════════════════════════════════════════════════
# models.py
# ══════════════════════════════════════════════════════════════════
from django.conf import settings
from django.db import models


class Note(models.Model):
    """یادداشت کاربر — با owner و timestamps."""
    owner = models.ForeignKey(
        settings.AUTH_USER_MODEL,
        on_delete=models.CASCADE,
        related_name="notes",
    )
    title = models.CharField(max_length=200)
    body = models.TextField(blank=True)
    is_pinned = models.BooleanField(default=False)
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

    class Meta:
        ordering = ["-is_pinned", "-created_at"]
        indexes = [
            models.Index(fields=["owner", "-created_at"]),
        ]

    def __str__(self):
        return f"{self.owner.username}: {self.title}"


# ══════════════════════════════════════════════════════════════════
# views.py — CBV با LoginRequiredMixin
# ══════════════════════════════════════════════════════════════════
from django.contrib.auth.mixins import LoginRequiredMixin
from django.urls import reverse_lazy
from django.views.generic import (
    ListView, DetailView, CreateView, UpdateView, DeleteView,
)
from .models import Note


class NoteListView(LoginRequiredMixin, ListView):
    """لیست یادداشت‌های کاربر جاری."""
    model = Note
    template_name = "notebook/note_list.html"
    context_object_name = "notes"
    paginate_by = 10

    def get_queryset(self):
        # فقط یادداشت‌های خود کاربر + جستجوی ساده
        qs = Note.objects.filter(owner=self.request.user)
        q = self.request.GET.get("q", "").strip()
        if q:
            from django.db.models import Q
            qs = qs.filter(Q(title__icontains=q) | Q(body__icontains=q))
        return qs


class NoteDetailView(LoginRequiredMixin, DetailView):
    model = Note
    template_name = "notebook/note_detail.html"
    context_object_name = "note"

    def get_queryset(self):
        # کاربر فقط یادداشت خودش را ببیند
        return Note.objects.filter(owner=self.request.user)


class NoteCreateView(LoginRequiredMixin, CreateView):
    model = Note
    template_name = "notebook/note_form.html"
    fields = ["title", "body", "is_pinned"]
    success_url = reverse_lazy("notebook:note_list")

    def form_valid(self, form):
        # owner را خودکار set کن
        form.instance.owner = self.request.user
        return super().form_valid(form)


class NoteUpdateView(LoginRequiredMixin, UpdateView):
    model = Note
    template_name = "notebook/note_form.html"
    fields = ["title", "body", "is_pinned"]
    success_url = reverse_lazy("notebook:note_list")

    def get_queryset(self):
        return Note.objects.filter(owner=self.request.user)


class NoteDeleteView(LoginRequiredMixin, DeleteView):
    model = Note
    template_name = "notebook/note_confirm_delete.html"
    success_url = reverse_lazy("notebook:note_list")

    def get_queryset(self):
        return Note.objects.filter(owner=self.request.user)


# ══════════════════════════════════════════════════════════════════
# urls.py
# ══════════════════════════════════════════════════════════════════
# app_name = "notebook"
# urlpatterns = [
#     path("", NoteListView.as_view(), name="note_list"),
#     path("new/", NoteCreateView.as_view(), name="note_create"),
#     path("<int:pk>/", NoteDetailView.as_view(), name="note_detail"),
#     path("<int:pk>/edit/", NoteUpdateView.as_view(), name="note_update"),
#     path("<int:pk>/delete/", NoteDeleteView.as_view(), name="note_delete"),
# ]

# ══════════════════════════════════════════════════════════════════
# tests.py — حداقل ۳ تست
# ══════════════════════════════════════════════════════════════════
from django.test import TestCase, Client
from django.contrib.auth import get_user_model
from django.urls import reverse
from .models import Note

User = get_user_model()


class NoteModelTest(TestCase):
    def setUp(self):
        self.user = User.objects.create_user("test", password="pass")
        self.note = Note.objects.create(
            owner=self.user, title="Test", body="Body"
        )

    def test_str(self):
        self.assertIn("Test", str(self.note))

    def test_owner_relation(self):
        self.assertEqual(self.user.notes.count(), 1)


class NoteViewTest(TestCase):
    def setUp(self):
        self.client = Client()
        self.user = User.objects.create_user("test", password="pass")
        self.note = Note.objects.create(
            owner=self.user, title="My Note"
        )

    def test_list_requires_login(self):
        response = self.client.get("/notes/")
        self.assertEqual(response.status_code, 302)   # redirect to login

    def test_list_shows_own_notes(self):
        self.client.login(username="test", password="pass")
        response = self.client.get("/notes/")
        self.assertContains(response, "My Note")

    def test_cannot_see_others_notes(self):
        other = User.objects.create_user("other", password="pass")
        Note.objects.create(owner=other, title="Secret")
        self.client.login(username="test", password="pass")
        response = self.client.get("/notes/")
        self.assertNotContains(response, "Secret")

    def test_create_note(self):
        self.client.login(username="test", password="pass")
        response = self.client.post("/notes/new/", {
            "title": "New Note",
            "body": "Content",
        })
        self.assertEqual(response.status_code, 302)
        self.assertEqual(Note.objects.count(), 2)
