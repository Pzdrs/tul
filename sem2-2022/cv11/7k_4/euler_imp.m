function [t,y] = euler_imp(f,a,b,y0,h)
n=(b-a)/h;  %calculates number of iterations
y(1)=y0;    %defines initial value of speed
t(1)=a;     %defines initial value of time
for i=1:n
    t(i+1)=t(i)+h; %calculates time after each iteration
    y_new=y(i)+h*(f(t(i),y(i))); %calculate new speed value
    y(i+1)=y(i)+h*f(t(i+1),y_new); %defines updated speed in array
    
end
t=t/3600;   %converts time to hours
y=y*3.6;    %converts speed to km/h
end

