// ============================================================
// جلسه ۴۹ — Dockerfile Concept
// ============================================================

string dockerfile = """
    # Stage 1: Build
    FROM mcr.microsoft.com/dotnet/sdk:8.0 AS build
    WORKDIR /src
    COPY *.csproj .
    RUN dotnet restore
    COPY . .
    RUN dotnet publish -c Release -o /app/publish

    # Stage 2: Runtime
    FROM mcr.microsoft.com/dotnet/aspnet:8.0 AS runtime
    WORKDIR /app
    COPY --from=build /app/publish .
    EXPOSE 8080
    ENV ASPNETCORE_URLS=http://+:8080
    ENV ASPNETCORE_ENVIRONMENT=Production
    ENTRYPOINT ["dotnet", "ClinicApp.dll"]
    """;

Console.WriteLine("=== Dockerfile (Multi-stage) ===");
foreach (var line in dockerfile.Split('\n'))
    Console.WriteLine($"  {line}");

Console.WriteLine("\n=== Docker Commands ===");
Console.WriteLine("  docker build -t clinic-app .");
Console.WriteLine("  docker run -p 8080:8080 clinic-app");
Console.WriteLine("  docker run -e ConnectionStrings__Default='...' clinic-app");

Console.WriteLine("\n=== .dockerignore ===");
foreach (var item in new[] { "bin/", "obj/", ".git/", "*.md" })
    Console.WriteLine($"  {item}");

Console.WriteLine("\n=== Benefits ===");
Console.WriteLine("  ✓ Consistent environment");
Console.WriteLine("  ✓ Easy scaling (Kubernetes)");
Console.WriteLine("  ✓ Isolated dependencies");
