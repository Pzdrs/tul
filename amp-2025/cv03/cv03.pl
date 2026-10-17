c:- consult("cv03.pl").

% Soucet pozitivnich prvku v listu
% ----------------------------
% Trivial case
soucet_plus([], 0).

% Positive element case - continue recursively
soucet_plus([H|T], Result) :-
    H > 0,
    soucet_plus(T, Temp),
    Result is Temp + H.

% Negative element case
soucet_plus([H|T], Result) :-
    H =< 0,
    soucet_plus(T, Result).

% Rozdelovani listu
% -----------------------------
% Dva prvni head prvky poslu do jejich respective
% listu a rekurzivne jedu dal na zbytku
rozdel([A, B | Tail], [A | P1], [B | P2]) :-
    rozdel(Tail, P1, P2).

% Empty list pripad
rozdel([], [], []).

% V pripade, ze v listu zbyva posledni prvek,
% manualne ho priradim a v rekurzi nepokracuju.
% Jakoby terminating step
rozdel([A], [A], []).

% Remove element

% Trivial case
remove(_, [], []).
% Jakmile najdem prvni occurance tak finito
remove(Head, [Head|Tail], Tail) :- !.
remove(X, [Head|Tail], [Head|ReducedTail]) :-
    remove(X, Tail, ReducedTail).

% Unique - jestli je jakoby mnozina
unique([]).
unique([Head|Tail]) :-
    \+ member(Head, Tail),
    unique(Tail).