document.addEventListener('DOMContentLoaded', function(){

    document.getElementsByClassName('submit-sorting-form')[0].addEventListener('submit', function(event) {
        event.preventDefault();

        const url = 'array';
        const data = new FormData(event.target);

        fetch(url, {
            method: 'POST',
            body: data
        })
            .then(response => response.json())
            .then(
                json => {
                    document.querySelector('.choose-sorting-form').classList.remove('hidden');

                    let option = document.createElement('option');
                    option.value = json.id;
                    option.text = json.name;

                    document.querySelector('.array-fieldset-selector').appendChild(option);
                })
        .catch(function(exception) {
            console.log('Завершилось с ошибкой');
        });

        return false;

    });

    document.getElementsByClassName('choose-sorting-form')[0].addEventListener('submit', function(event) {
        event.preventDefault();
        const url = 'array';
        const data = new FormData(event.target);

        fetch(url + "?arrayId=" +  data.get('arrayId'), {
            method: 'GET',
        })
            .then(
                response => response.text()
            )
            .then(text => {
                document.querySelector('.sort-result').classList.remove('hidden');
                document.getElementsByClassName('sort-result-output')[0].innerHTML = text;
            })
            .catch(function(exception) {
                console.log('Завершилось с ошибкой');
            });

        return false;
    });

});