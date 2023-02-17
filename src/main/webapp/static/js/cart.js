function main() {
    cartButtonsListener()
}

main()

const totalAmount = document.querySelector(".total-amount")
const removeButton = document.querySelector(".Action")

removeButton.addEventListener('click', function emptyCart(event){

})


function cartButtonsListener() {
    const counterButtons = document.querySelectorAll(".btnCart")
    counterButtons.forEach(counterButton => counterButton.addEventListener('click', function updateCart(event) {
        if (counterButton.classList.contains("plus")) {
            increaseQuantity(event)

        } else {
            decreaseQuantity(event)
        }
    }))
}

async function increaseQuantity(event) {
    const productId = event.currentTarget.getAttribute("product")
    await apiPost(`/api/edit-quantity?product=${productId}&changer=plus`)
    let valueDiv = document.querySelector(`.count[product="${productId}"]`)
    let value = parseInt(valueDiv.innerHTML)
    value++
    valueDiv.innerHTML = (value).toString()
    let productSumDiv = document.querySelector(`.amount[product="${productId}"]`)
    let priceH = document.querySelector(`.subtitleShoppingCart[product="${productId}"]`)
    let price = parseFloat(priceH.innerHTML)
    let productSum = price * value
    productSumDiv.innerHTML = Number((productSum).toFixed(2)).toString()
    let totalNewAmount = parseFloat(totalAmount.innerHTML)
    totalNewAmount += price;
    totalAmount.innerHTML = Number((totalNewAmount).toFixed(2)).toString()

}


async function decreaseQuantity(event) {
    const productId = event.currentTarget.getAttribute("product")
    await apiPost(`/api/edit-quantity?product=${productId}&changer=minus`)
    let valueDiv = document.querySelector(`.count[product="${productId}"]`)
    let value = parseInt(valueDiv.innerHTML)
    value--
    valueDiv.innerHTML = (value).toString()
    let productSumDiv = document.querySelector(`.amount[product="${productId}"]`)
    let priceH = document.querySelector(`.subtitleShoppingCart[product="${productId}"]`)
    let price = parseFloat(priceH.innerHTML)
    let productSum = price * value
    productSumDiv.innerHTML = Number((productSum).toFixed(2)).toString()
    let totalNewAmount = parseFloat(totalAmount.innerHTML)
    totalNewAmount -= price;
    totalAmount.innerHTML = Number((totalNewAmount).toFixed(2)).toString()

}


async function apiPost(url) {
    let response = await fetch(url, {
        method: "POST",
    });
    console.log(response)
    /*if (response.ok) {
        return await response.json();
    }*/
}