using LojaManga.Models;
using LojaManga.Pages;
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



builder.Services.AddRazorPages();

builder.Services.AddScoped<DbInitializer>();

// Atualize para Scoped
builder.Services.AddScoped<ServicoBancoDeDados>();

// Add services to the container.
builder.Services.AddRazorPages();
builder.Services.AddSingleton<DbInitializer>();


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
