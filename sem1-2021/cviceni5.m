clc;clear all;echo off;close all;

%1
figure(1)
theta = 0:pi/3:2*pi;
r=ones(1,7);
polarplot(theta,r,'k-','LineWidth',5);
grid on;
    

%2
%viz funkce_s_nargin_cv5.m

%3a
x = (-5:0.5:5);
figure(2)
plot(x,funkce_s_nargin_cv5(x,0));
hold on
plot(x,funkce_s_nargin_cv5(x,1));
plot(x,funkce_s_nargin_cv5(x,2));
hold off
title("Hodnoty funkcí f(x), f(x,1) a f(x,2)");
legend('f(x,0)','f(x,1)','f(x,2)');
xlabel('x');
ylabel('y');

%3b
figure(3)
subplot(3,1,1)
plot(x,funkce_s_nargin_cv5(x,0), "Color", [1 0 1]);
title("f(x,0)")
xlabel('x')
ylabel('y')
subplot(3,1,2)
plot(x,funkce_s_nargin_cv5(x,1),"Color", [0 0 1]);
title("f(x,1)")
xlabel('x')
ylabel('y')
subplot(3,1,3)
plot(x,funkce_s_nargin_cv5(x,2),"Color",[1 1 0]);
title("f(x,2)")
xlabel('x')
ylabel('y')
%figure


%4
figure(4)
[x y] = meshgrid(-5:0.1:5 ,-6:0.2:6);
z = funkce_s_nargin_cv5(x,y);
mesh(x,y,z)
