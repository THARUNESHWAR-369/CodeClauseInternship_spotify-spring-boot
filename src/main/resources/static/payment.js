var stripe = Stripe('pk_test_51O1t3HSCLPyPm8AeuDB5R9wWiO78lUnTKVXn7YuzqAaEnNqigEEYOZiIrZAkBZFOSA3hnMIN2Opj3cpt1YjLNbUf00OXVqPRDn')

var elements = stripe.elements();
var cardElement = elements.create('card');

cardElement.mount('#card-element');

var cardErrors = document.getElementById('card-errors');

cardElement.on('change', function(event) {
    if (event.error) {
        cardErrors.textContent = event.error.message;
    } else {
        cardErrors.textContent = '';
    }
});

var form = document.getElementById('payment-form');

// document.getElementById("checkout").addEventListener("click", ()=>{
//     console.log("Checkout Clicked....")
//     document.getElementById("model").style.display="block";
// })
form.addEventListener('submit', function(event) {
    event.preventDefault();


    stripe.createToken(cardElement).then(function(result) {
        if (result.error) {
            cardErrors.textContent = result.error.message;
        } else {
            stripeTokenHandler(result.token);
        }
    });
});

function stripeTokenHandler(token) {
    // Send the token to your server
    fetch('/charge', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ token: token.id })
    })
        .then(response => response.json())
        .then(data => {
            if (data.error) {
                cardErrors.textContent = data.error;
            } else {
                alert('Payment Successful! Charge ID: ' + data.chargeId);
            }
        });
}
