clear;
clc;
% checkRoots(1, [1 -4 2 2 1 6]);
% checkRoots(2, [1 -3 3 -1]);
% checkRoots(3, [1, 37]);
% checkRoots(4, [10, -120]);
% checkRoots(5, [1 4 -15 -70 -16 96]);
checkRoots([1 1 -3 -7 -40 -60]);
checkRoots([7 7 -21 -49 -280 -420]);
checkRoots([8, 1 1 -3 -7 -40 -60 0 0]);
function checkRoots(polynom)
    [y, M] = find_int_roots(polynom);
    r = roots(polynom);
    r = real(r);
    r = r(round(r) == r);
    y
    r
end