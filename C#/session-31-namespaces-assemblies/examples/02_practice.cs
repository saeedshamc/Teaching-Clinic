// ============================================================
// جلسه ۳۱ — Namespaces: using و alias
// ============================================================

using DomainUser = App.Domain.User;
using ApiUser = App.Api.User;
using static System.Math;
using System.Text;

var d = new DomainUser();
var a = new ApiUser();
Console.WriteLine($"Domain: {d.Name}, Api: {a.Name}");
Console.WriteLine($"Sqrt(16) = {Sqrt(16)}");

var text = new StringBuilder();
text.Append("سلام ");
text.Append("Namespace!");
Console.WriteLine(text.ToString());

namespace App.Domain { public class User { public string Name = "Domain"; } }
namespace App.Api { public class User { public string Name = "Api"; } }
