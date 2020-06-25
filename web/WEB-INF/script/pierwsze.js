Pierwsze = function (ile) {
    this.ile = ile;
    this.pierwsze = [];
    this.pomocnicza=[];
};

Pierwsze.prototype.jestPierwsza = function (value) {
    if (value === 0 || value === 1)
        return false;
    if (value === 2)
        return true;
    for (var i = 2; i < value; i++)
        if (value % i === 0)
            return false;
    return true
};

Pierwsze.prototype.validate = function () {
    if (this.ile === 'NaN'  || this.ile<0|| this.ile == null || !this.ile) {
        return false;
    }
    return true;
};

Pierwsze.prototype.uzupelnijTab = function () {
    this.primes = [];
    var obecna = 2;
    while (this.pierwsze.length !== this.ile) {
        if (this.jestPierwsza(obecna)) {
            this.pierwsze.push(obecna);
        }
        obecna++;
    }
};

Pierwsze.prototype.generujPierwsze = function () {
    if (this.validate()) {
        this.uzupelnijTab()
    } else {
        alert("niepoprawne dane")
    }
};

Pierwsze.prototype.getPrimes = function () {
    return this.pierwsze;
};
var ol = document.createElement('ol');
function podpiecie() {
    var btn = document.getElementById('oblicz');
    btn.addEventListener('click', function (_) {
        var ile = document.getElementById('liczba').value;
        var PierwszeObiekt = new Pierwsze(parseInt(ile));
        PierwszeObiekt.generujPierwsze();

        var primesListView = document.getElementById('listaPierwszych');
        if (primesListView.hasChildNodes()) {
            while (primesListView.firstChild) {
                primesListView.removeChild(primesListView.firstChild);
            }
        }
        PierwszeObiekt.getPrimes().forEach(printPrime)
        primesListView.appendChild(ol);
    })
}

function printPrime(element) {

    var primeText = document.createTextNode(element);

    var li = document.createElement('li');
    li.appendChild(primeText);
    ol.appendChild(li);
}