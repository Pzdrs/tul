function x = Newton(x0, f, eps, maxiter, delta)
x=x0;
i=0;
while abs(f(x)) > eps && i < maxiter
    x = x - delta * f(x) / (f(x+delta) - f(x));
    i = i+1;
end
if i > maxiter
    x = NaN;
end
end