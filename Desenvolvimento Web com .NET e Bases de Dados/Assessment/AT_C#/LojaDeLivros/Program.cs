using LojaDeMangas.Models;
using LojaDeMangas.Pages;
using Microsoft.AspNetCore.Identity;
using Microsoft.EntityFrameworkCore;

using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.DependencyInjection;

var builder = WebApplication.CreateBuilder(args);

var connectionString = builder.Configuration.GetConnectionString("DefaultConnection");
builder.Services.AddDbContext<LojaDbContext>(x => x.UseSqlServer(connectionString));

//builder.Services.AddIdentity<ApplicationUser, IdentityUser>(options =>
//{
//    // Configurações do Identity, se necessário
//    options.SignIn.RequireConfirmedAccount = true;
//}).AddUserStore<LojaDbContext>();

builder.Services.ConfigureApplicationCookie(options =>
{
    options.Cookie.HttpOnly = true;
    options.ExpireTimeSpan = TimeSpan.FromMinutes(60);
    options.LoginPath = "/Account/Login";
    options.AccessDeniedPath = "/Account/AccessDenied";
    options.SlidingExpiration = true;
});

builder.Services.AddHttpContextAccessor();

builder.Services.AddRazorPages();

// Add services to the container.
builder.Services.AddRazorPages();
//builder.Services.AddSingleton<DbInitializer>();
builder.Services.AddScoped<DbInitializer>();

//builder.Services.AddHostedService(provider =>
//{
//    using (var scope = provider.CreateScope())
//    {
//        var initializer = scope.ServiceProvider.GetRequiredService<DbInitializer>();
//        return new DbInitializerHostedService(initializer);
//    }
//});

var app = builder.Build();
// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Error");
    // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
    app.UseHsts();
}

app.UseHttpsRedirection();
app.UseStaticFiles();

app.UseRouting();

app.UseAuthorization();

app.MapRazorPages();

app.Run();


public class DbInitializerHostedService : IHostedService
{
    private readonly DbInitializer _initializer;

    public DbInitializerHostedService(DbInitializer initializer)
    {
        _initializer = initializer;
    }

    public Task StartAsync(CancellationToken cancellationToken)
    {
        return _initializer.StartAsync(cancellationToken);
    }

    public Task StopAsync(CancellationToken cancellationToken)
    {
        return Task.CompletedTask;
    }
}