% 1)
x=-10:.1:10;
f=@(x)(sin(x)./x)+exp(1).^-((x-4).^2);
%plot(x, f(x))
fminbnd(f, 2, 4);
fminbnd(f, 4, 6);

% 2)
g=@(x)x.^4/4 - 41*x.^3/6 + 209*x.^2/4 - 135*x;
%plot(-10:.1:20, g(-10:.1:20))
fminbnd(g, 0, 15);

% 3)
h=@(x)(100.*(x(2)-x(1).^2).^2+(1-x(1)).^2);
fminsearch(h, [-2, 2])

% 4)
constraints = [1, -1, 1; 3, 2, 4; 3, 2, 0];
constraints2= [20, 42, 30];

result = linprog([-5, -2, -6], constraints, constraints2, [], [],  [0, 0, 0]);

% 5)
constraints = [1, -1, 1; 1, 2, -6];
constraints2 = [-1, 5];

quadprog([8, 2, 0; 2, 4 -3; 0 -3, 6], [-1, 0, 0], constraints, constraints2, [], [], [0, 0, 0], []);