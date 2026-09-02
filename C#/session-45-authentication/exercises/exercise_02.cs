// ============================================================
// تمرین ۲ جلسه ۴۵ — CanEditPost Policy (پاسخ نمونه)
// ============================================================

class Post { public int Id { get; set; } public string AuthorId { get; set; } = ""; public string Title { get; set; } = ""; }
class User { public string Id { get; set; } = ""; public string Name { get; set; } = ""; public List<string> Roles { get; set; } = new(); }

bool CanEditPost(User user, Post post)
    => user.Roles.Contains("Admin")
    || (user.Roles.Contains("Author") && user.Id == post.AuthorId);

var admin = new User { Id = "1", Name = "Admin", Roles = new() { "Admin" } };
var author = new User { Id = "2", Name = "Ali", Roles = new() { "Author" } };
var post = new Post { Id = 1, AuthorId = "2", Title = "My Post" };

Console.WriteLine($"Admin edit: {CanEditPost(admin, post)}");
Console.WriteLine($"Author edit own: {CanEditPost(author, post)}");
Console.WriteLine($"Author edit other's: {CanEditPost(author, new Post { AuthorId = "99" })}");
