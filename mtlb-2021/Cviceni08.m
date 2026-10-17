% 1)
syms x  
f1 = @(x) 1./(2+cos(x));
integral(f1, 0, 2*pi);
int(f1, x, 0, 2*pi);

f2 = @(x) x.*atan(x);
integral(f2, 0, sqrt(3));
int(f2, x, 0, sqrt(3));

f3 = @(x) sqrt(1-sin(2*x));
integral(f3, 0, 2*pi);
int(f3, x,  0, 2*pi);

f4 = @(x) exp(1).^(-x.^2);
integral(f4, -10, 5);
int(f4, x, -10, 5);

integral(f4, -Inf, Inf);
int(f4, x, -Inf, Inf);

% 2)
syms n

vpasum(((-1)^n)*(1/2^(n-1)), 1, Inf);
symsum(((-1)^n)*(1/2^(n-1)), n, 1, Inf);

vpasum(1/(n*(n+1)), 1, Inf);
symsum(1/(n*(n+1)), n, 1, Inf);

vpasum(((-1)^n)*(1/n), 1, Inf);
symsum(((-1)^n)*(1/n), n, 1, Inf);

vpasum((2*n-1)/2^n, 1, Inf);
symsum((2*n-1)/2^n, n, 1, Inf);

% 3)
figure(1)
syms a;
x = -10:0.1:10;
y = double(subs(det([1 7 a; (a^2) 3 (1-a); 0 5 6]), a, x));

plot(x, y, x(islocalmax(y)), y(islocalmax(y)),'o')
hold on
plot(x(islocalmin(y)),y(islocalmin(y)),'o')

% nema lokalni minimum/maximum
figure(2)
y2 = subs(det([a 8 (-3*a); 1 ((1-a)^2) a; 3 -1 4]), a, x);
plot(x,y2)


