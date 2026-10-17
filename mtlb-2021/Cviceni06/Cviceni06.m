x = -10:.1:.9;
xx = 1.1:.1:10;
y = atan((x+1)./(x-1));
yy= atan((xx+1)./(xx-1));

plot(x, y, xx, yy, Color='g')
xlabel('x')
ylabel('y')
legend('f(x) = arctan((x+1)/(x-1))', Location='southeast')
title('Průběh funkce y = arctan((x+1)/(x-1))')

x2 = 0:.01:2;
y2 = x2.^x2;

figure
plot(x2,y2)
xlabel('x')
ylabel('y')
legend('h(x) = x^x', Location='southeast')
title('Průběh funkce y = x^x')
 
x3 = [-10:.1:-1 1:.1:10];
y3 = x3./sqrt(x3.^2-1);

figure
plot(x3, y3, Color='r')
xlabel('x')
ylabel('y')
legend('g(x) = x / sqrt(x^2 - 1)', Location='southeast')
title('Průběh funkce y = x / sqrt(x^2 - 1)')