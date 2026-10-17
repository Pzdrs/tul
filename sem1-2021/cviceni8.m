clear all; clc; echo off; close all;

%1
%1.1
syms x
krok = 0.01;
rozdeleni = 0:krok:2*pi-krok;
OM1 = sum(krok./(2+cos(rozdeleni+krok)))
f = 1/2+cos(x);
AN1 = vpa(int(f,x,0,2*pi))

%1.2
rozdeleni2 = 0:krok:sqrt(3)-krok;
OM2 = sum((rozdeleni2+krok/2).*atan(rozdeleni2+krok/2).*krok)
f2 = x*atan(x);
AN2 = vpa(int(f2,x,0,sqrt(3)))

%1.3
rozdeleni3 = 0:krok:2*pi-krok;
OM3 = sum(sqrt(1-sin(2*rozdeleni3+krok/2))*krok)
f3 = sqrt(1-sin(2*x));
AN3 = vpa(int(f3,x,0,2*pi))

%1.4
rozdeleni4 = -10:krok:5-krok;
OM4 = sum(exp((-(rozdeleni4+krok/2)).^2)*krok)
f4 = exp((-x)^2);
AN4 = vpa(int(f4,x,-10,5))

%1.5
rozdeleni5 = -20:krok:20-krok;
OM5 = sum(exp((-(rozdeleni4+krok/2)).^2)*krok)
AN5 = vpa(int(f4,x,-Inf,Inf))

%2
%2.1
a_sum1 = symsum((-1)^n,n,1,) 



