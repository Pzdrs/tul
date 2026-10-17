clc;clear all; echo off; close all;
%1
a = sym(sum((12/63+21/51)*(7/5)-22/7))

%2
syms x y
f = log(abs(cos(x))) + y * x * exp(-x^2)

%3
g = subs(f,x,1/y)

%4
g_y = subs(g,y,sym(3))
g_3 = vpa(g_y,100) 

%5

g_i = vpa(int(g,y,1,2),6)

krok=0.01;
rozdeleni = 1:krok:2-krok;
ObdelnikovaMetoda= sum(log(abs(cos(1./rozdeleni+krok))+(rozdeleni+krok).*1./(rozdeleni+krok).^2)*krok)

%6
i_neurc = int(f,x)

%7
syms k n
analyticky_sum = symsum(sin((2*pi/3)*k),k,1,n)

vpa(subs(analyticky_sum,n,40))
k_num = 1:40;
sum(sin((2*pi/3)*k_num))

%8
limita = limit(f,x,pi/2)

%9
urcity_integral = int(diff(f,y),0,Inf) 

%10
A = [4 -3 1; 2 -6 9; 4 3 0];
b = [1 5 0]';
Soustava = vpa(inv(A)*b,2)
%neexistuje pro a=1

%11
Rozvinuti = expand(cos(3*x)-sin(3*x))

%12

%exp(-x^2+4x+9) = 1
Vysledek_solve = solve(str2sym('exp(-x^2+4*x-9)')-1)
p = [-1 4 -9];
Vysledek_roots = roots(p)

