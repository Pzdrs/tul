clear all; clc; echo off; close all;

%1
x = -10:0.01:10;
f = @(x) sin(x)/(x) + exp(-(x-4)^2);
figure(1)
fplot(f,[-10 10],'r','LineWidth', 2)
min1 = fminbnd(f,2,4);
min2 = fminbnd(f,4,6);


%2
f2 = @(x) (1/4*x^4) - (41/6*x^3) + (209/4*x^2)- (135*x);
figure(2)
fplot(f2, 'green', 'LineWidth',2)
minf2 = fminbnd(f2,10,20);

%3
f3 = @(x) 100*(x(2)-x(1).^2).^2 + (1-x(1)).^2;
x1 = -2:0.01:2;
x2 = -2:0.01:2;
f_x = @(x1,x2) 100*(x2-x1.^2).^2 + (1-x1).^2;
figure(3)
[x1 x2] = meshgrid(x1,x2);
z = funkce_03(x1,x2);
mesh(x1,x2,z)
minf3 = fminsearch(f3,[0,0]);

%4 
f4 = -[-5 2 6];
A = [1 -1 1
     3 2 4
     3 2 0];
b = [20 42 30];
lb = [0 0 0];
reseni_f4 = linprog(f4,A,b,[],[],lb,[])

%5

%H = [4 1; -3 2; 1 3];
%f5 = [-1; 0; 0];
%A2 = [1 -1 1; 1 2 -6;];
%b2 = [-1;5];
%lb2 = zeros(3,1);
%reseni_f5 = quadprog(H,f5,A2,b2,[],[],lb2,[])