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
    "endDate": "2020-03-26",
    "discountedPrice": 50.0,
    "adText": "Nieuwe goede aanbieding voor maar 50 euros  "
}

const CATEGORY_BODY = (name) => ({
    "image": "https://via.placeholder.com/600x400/f2f2f2?text=e2e%20test",
    "name": name,
    "description": "description"
})

const PRODUCT_BODY = ({ name, price, discountIds}) => ({
    "name": name,
    "price": price,
    "stock": 10,
    "discountIds": [...discountIds]
});

async function startE2E() {
    // create customer
    const { data: customer } = await axios.post(`${ROOT_API_URL}/customers`, CUSTOMER_BODY);
    log({customer})

    // get customer JWT token
    const { data: { token } } = await axios.post(`${ROOT_API_URL}/user/generate`, AUTH_BODY);
    log({ token })

    // auth header is created here
    const headers = { headers: { 'Authorization': `Bearer ${token}` } }

    // create discount
    const { data: discount } = await axios.post(`${ROOT_API_URL}/authed/discounts`, DISCOUNT_BODY, headers);
    log({ discount })

    // create category 'New'
    // const { data: categoryNew } = await
    axios.post(`${ROOT_API_URL}/authed/categories`, CATEGORY_BODY('New'), headers)
        .then(succes)
        .catch(error);
    // log({ categoryNew})
    // create category 'Featured'
//     const { data: categoryFeatured } = await axios.post(`${ROOT_API_URL}/authed/categories`, CATEGORY_BODY('Featured'), headers);
//     // create category 'Sale'
//     const { data: categorySale } = await axios.post(`${ROOT_API_URL}/authed/categories`, CATEGORY_BODY('Sale'), headers);
//     log([categoryNew, categoryFeatured, categorySale])
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
