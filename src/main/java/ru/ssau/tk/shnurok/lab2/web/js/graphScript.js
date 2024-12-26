document.addEventListener('DOMContentLoaded', () => {
    const canvas = document.getElementById('functionGraph').getContext('2d');

    const chart = new Chart(canvas, {
        type: 'line',
        data: {
            labels: [],
            datasets: [{
                label: 'Функция',
                data: [],
                borderColor: 'green', // Зеленый цвет
                borderWidth: 2,
                fill: false,
            }],
        },
        options: {
            responsive: true,
            scales: {
                x: { title: { display: true, text: 'X' } },
                y: { title: { display: true, text: 'Y' } },
            },
        },
    });

    function updateGraph() {
        const rows = document.querySelectorAll('tbody tr');
        const xValues = [];
        const yValues = [];

        rows.forEach(row => {
            const xInput = row.cells[0].querySelector('input'); // Значение из первой ячейки (X)
            const yInput = row.cells[1].querySelector('input'); // Значение из второй ячейки (Y)

            if (xInput && yInput) {
                const x = parseFloat(xInput.value);
                const y = parseFloat(yInput.value);

                xValues.push(x);
                yValues.push(y);
            }
        });

        chart.data.labels = xValues;
        chart.data.datasets[0].data = yValues;
        chart.update();
    }

    const tableBody = document.querySelector('tbody');
    tableBody.addEventListener('input', updateGraph);
    updateGraph();
});
