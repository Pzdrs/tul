function [t,y] = euler_exp(f,a,b,y0,h) %defines function
n=(b-a)/h;  %calculates number of iterations
y(1)=y0;    %defines initial value of speed
t(1)=a;     %defines initial value of time
for i=1:n
    y(i+1)=y(i)+h*f(t(i),y(i)); %calculates speed after each iteration
    t(i+1)=t(i)+h;  %calculates time after each iteration
end
t=t/3600;   %converts time to hours
y=y*3.6;    %converts speed to km/h
end

