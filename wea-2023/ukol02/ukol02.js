// Script src byl v indexu spatne takze jsem ho prepsal
// Taky jsem pridal defer do script tagu, slo by to vyresit i event listenerem, ale defer bylo rychlejsi
let notes_shown = true;
const button = document.querySelector('button#prepinac');
button.addEventListener('click', () => {
    document.querySelectorAll('p.poznamka').forEach(p => {
        p.style.display = notes_shown ? 'none' : 'block';
    });
    button.innerText = notes_shown ? 'Zobrazit poznámky' : 'Skrýt poznámky';
    notes_shown = !notes_shown;
});
