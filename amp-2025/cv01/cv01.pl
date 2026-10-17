c:- consult("cv01.pl").

% FACTS
% Individual genders
male(alfons).
male(jiri).
male(emil).
male(bohumil).
male(cyril).
male(otakar).

female(ema).
female(jitka).
female(vera).
female(ivana).
female(lenka).
female(zuzana).

% Parents
% 1st level
parent(alfons, vera).
parent(alfons, emil).
parent(ema, vera).
parent(ema, emil).
parent(jiri, ivana).
parent(jitka, ivana).
% 2nd level
parent(emil, cyril).
parent(ivana, cyril).
parent(bohumil, lenka).
% 3rd level
parent(cyril, zuzana).
parent(cyril, otakar).
parent(lenka, zuzana).
parent(lenka, otakar).

% RULES
% Grandparent and great-grandparent
grandparent(Self, Child):-
    parent(Self, P),
    parent(P, Child).
great_grandparent(Self, Child):-
    grandparent(Self, P),
    parent(P, Child).

% Children
child(Self, Parent):-
    parent(Parent, Self).
grandchild(Self, Grandparent):-
    grandparent(Grandparent, Self).
great_grandchild(Self, Great_grandparent):-
    great_grandparent(Great_grandparent, Self).

% Father and mother relationships
father(Self, Child):-
    male(Self),
    parent(Self, Child).
mother(Self, Child):-
    female(Self),
    parent(Self, Child).

% Siblings
% Generalized siblings (they share at least one common parent regardless of gender)
sibling(Self, Sibling):-
    parent(P, Self),
    parent(P, Sibling),
    Self \== Sibling.
% Full siblings (they share the same father AND the same mother)
% Unnecessary, as we have only biological siblings in this lineage
full_sibling(Self, Sibling):-
    father(F, Self), father(F, Sibling),
    mother(M, Self), mother(M, Sibling),
    Self \== Sibling.
brother(Self, Sibling):-
    male(Self),
    sibling(Self, Sibling).
sister(Self, Sibling):-
    female(Self),
    sibling(Self, Sibling).

% Uncles and aunts
uncle(Self, Nibling):-
    male(Self),
    parent(P, Nibling),
    sibling(Self, P).
aunt(Self, Nibling):-
    female(Self),
    parent(P, Nibling),
    sibling(Self, P).
