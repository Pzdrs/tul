clc;clear all
p=20;           %defines p value
k=0.516;        %defines k value
m=70+15;        %defines mass value
y0=30/3.6;      %defines initial speed in m/s
f=@(t,v) (p-(k*v^2))/m; %defines function
h=1;            %defines step size
a=0;            %defines starting time
b=100;          %defines ending time
%% Explicit Euler
[t,y]=euler_exp(f,a,b,y0,h); %calls function
for i=1:length(y)-1
    if abs(y(i)-y(i+1))<0.001 %checks for steady speed
        v_end=y(i); %steady speed value
        t_end=t(i); %steady speed time
    end
end
fprintf('The steady speed value for Explicit Euler method is %.5fkm/h at %.5fsec.\n',v_end,t_end*3600)
figure
plot(t,y,'r-') %plots speed (km/h) and time(h)
%% Implicit Euler
[t,y]=euler_imp(f,a,b,y0,h); %calls function
for i=1:length(y)-1
    if abs(y(i)-y(i+1))<0.001 %checks for steady speed
        v_end=y(i);    %steady speed value
        t_end=t(i);    %steady speed time
    end
end
fprintf('The steady speed value for Implicit Euler method is %.5fkm/h at %.5fsec.\n',v_end,t_end*3600)
hold on
plot(t,y,'b--') %plots speed (km/h) and time(h)
%% ode45
tspan=[a b];  %defines time span
[t,y]=ode45(@(t,y) (p-(k*y^2))/m,tspan,y0); %calculates y using ode45
t=t/3600;   %converts time to hours
y=y*3.6;    %converts speed to km/h
for i=1:length(y)-1
    if abs(y(i)-y(i+1))<0.001 %checks for steady speed
        v_end=y(i);    %steady speed value
        t_end=t(i);    %steady speed time
    end
end
fprintf('The steady speed value for ode45 method is %.5fkm/h at %.5fsec.\n',v_end,t_end*3600)
plot(t,y,'k--')  %plots speed (km/h) and time(h)
legend('Explicit','Implicit','ode45')
xlabel('time [h]')
ylabel('speed [km/h]')
hold off
grid on