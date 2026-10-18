const COLORS = ['primary', 'secondary', 'success', 'danger', 'warning', 'info', 'light', 'dark'];

function handle(event, banner) {
    const {target} = event;
    if (target == null) return;

    const color = [...target.classList]
        .map(clazz => clazz.split('-').pop())
        .find(clazz => COLORS.includes(clazz));


    banner.classList.remove(
        ...[...banner.classList].filter(clazz => COLORS.includes(clazz.split('-').pop()))
    );
    banner.classList.add(`bg-${color}`);
}

window.addEventListener('load', () => {
    const banner = document.querySelector('#banner');
    document.querySelectorAll('button')
        .forEach((button) => button.addEventListener('click', event => handle(event, banner)));
})
