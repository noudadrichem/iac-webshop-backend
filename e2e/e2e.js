const axios = require('axios');

const ROOT_API_URL = 'http://localhost:9091';
const CUSTOMER_BODY = {
    'name': 'Noud Adrichemn',
    'phone': '06-12345678',
    'email': 'info@noudadrichem.com'
}

const AUTH_BODY = {
    "id": 1,
    "userName": "Noud Adrichem",
    "role": "admin"
}

const DISCOUNT_BODY = {
    "startDate": "2020-03-20",
    "endDate": "2020-04-26",
    "discountedPrice": 50.0,
    "adText": "Nieuwe goede aanbieding voor maar 50 euros",
    "productIdList": [1,2]
}

const CATEGORY_BODY = (name) => ({
    "image": "https://via.placeholder.com/600x400/f2f2f2?text=e2e%20test",
    "name": name,
    "description": "description"
})

const PRODUCT_BODY = ({ name, price}) => ({
    "name": name,
    "price": price,
    "stock": 10,
    "descripiton": "beschrijving",
    "discountIds": []
});

async function startE2E() {
    // create customer
    const { data: customer } = await axios.post(`${ROOT_API_URL}/customers`, CUSTOMER_BODY);
    log({customer})

    const { data: customers } = await axios.get(`${ROOT_API_URL}/customers`, CUSTOMER_BODY);
    log({customers})

    // // get customer JWT token
    // const { data: { token } } = await axios.post(`${ROOT_API_URL}/user/generate`, AUTH_BODY);
    // log({ token })

    // auth header is created here
    const headers = {
        // headers: { 'Authorization': `Bearer ${token}` }
    }

    const { data: categories } = await axios.get(`${ROOT_API_URL}/categories`, headers);
    if (categories.length > 0) {
        // create categorry 'nieuw'
        const { data: categoryNew } = await axios.post(`${ROOT_API_URL}/categories`, CATEGORY_BODY('nieuw'), headers)
        // create category 'Featured'
        const { data: categoryFeatured } = await axios.post(`${ROOT_API_URL}/categories`, CATEGORY_BODY('featured'), headers);
        // create category 'Sale'
        const { data: categorySale } = await axios.post(`${ROOT_API_URL}/categories`, CATEGORY_BODY('sale'), headers);
        const categories = [categoryNew, categoryFeatured, categorySale];
    }
    log({categories})

    const mockProducts = [
        {
            name: "Laptop" + Math.round(Math.random() * 100),
            price: 1110.0
        },
        {
            name: "Desktop" + Math.round(Math.random() * 100),
            price: 2295.50
        },
        {
            name: "iPhone" + Math.round(Math.random() * 100),
            price: 1100.95
        },
    ]
    const products = [];
    for (let i = 0; i < mockProducts.length; i++) {
        const body = PRODUCT_BODY(mockProducts[i]);
        const { data: product } = await axios.post(`${ROOT_API_URL}/products`, body, headers);
        products.push(product);
    }
    log({ products })

    // create discount
    const { data: discount } = await axios.post(`${ROOT_API_URL}/discounts`, DISCOUNT_BODY, headers);
    log({ discount })

    // add product to category
    // log({
    //     URL: `${ROOT_API_URL}/categories/product/add/${categories[0].id}`,
    //     body: { productId: products[0].id }
    // })
    // const { data: productAndCategory } = await axios.put(`${ROOT_API_URL}/categories/product/add/${categories[0].id}`, { productId: products[0].id }, headers);
    // log({ productAndCategory })


    // create order
    const ORDER_BODY = {
        "date": "2020-04-10",
        "totalPrice": 10.0,
        "customerId": 1
    }
    const { data: order } = await axios.post(`${ROOT_API_URL}/orders`, ORDER_BODY, headers);
    log({ order })

    // orders
    const { data: orders } = await axios.get(`${ROOT_API_URL}/orders`, headers);
    log({ orders })

    // create order product
    const ORDER_PRODUCT_BODY = {
        "productId": products[0].id,
        "orderId": 1, //order.id,
        "amount": 2
    }
    const { data: orderProduct } = await axios.post(`${ROOT_API_URL}/orders/products`, ORDER_PRODUCT_BODY, headers);
    log({ orderProduct })


    const CHECKOUT_BODY = {
        "paymentMethod": "IDEAL",
        "customerId": customer.id || 1,
        "addressRequest": {
            "street": "Vosmaerstraat 91",
            "city": "Delft",
            "state": "Zuid-Holland",
            "postalCode": "2222DB",
            "country": "The Netherlands"
        },
        "orderId": 1
    }

    log({
        url: `${ROOT_API_URL}/checkout`,
        body: CHECKOUT_BODY
    })
    const { data: checkout } = await axios.post(`${ROOT_API_URL}/checkout`, CHECKOUT_BODY, headers);
    log({ checkout })

    console.log('http://localhost:9091' + '/checkout')

}

startE2E();

function succes(data) {
    log(data.data)
}

function error(data) {
    log(data)
}



// util
function log() {
    const ERROR = 'ERROR: ';
    const WARNING = 'WARNING: ';
    const INFO = 'INFO: ';

    const ANSI_RED = '\u001B[31m';
    const ANSI_BLUE = '\u001B[34m';
    const ANSI_YELLOW = '\u001B[33m';
    const ANSI_RESET = '\u001B[0m';

    const now = new Date();
    const hour = now.getHours();
    const minute = now.getMinutes();
    const seconds = now.getSeconds();

    console.log(`${ANSI_BLUE}${INFO}${hour}:${minute}:${seconds}:${ANSI_RESET}`, JSON.stringify(...arguments, null, 4))
}
