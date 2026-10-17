% 1)
((sym(12)/63)+(21/51))*(7/5)-(22/7);

% 2)
syms x y
f(x,y) = log(abs(cos(x))) + y * x * exp(1)^(-x^2);

% 3)
g(y) = subs(f, x, 1/y);

% 4)
vpa(g(3), 100);

% 5)
vpa(int(g, y, 1, 2), 6);

% 6)
int(f, x);

% 7)
syms k n
symsum(sin((pi*2/3)*k), 1, n);
vpasum(sin((pi*2/3).*k),1,40);

% 8)
syms x y
limit(f(x, y), x, pi/2);

% 9)
int(diff(f, x), 0, inf);

% 10)
syms a
A=[1 -3 a; 2 -6 9; -a 3 0];
det(A);
roots([-6 33 -27]);

% 11)
expand(cos(3*x)-sin(3*x));

% 12)
vpa(solve(exp(1)^(-x^2+4*x-9) == 1, x));

p = [-1 4 -9];
roots(p);