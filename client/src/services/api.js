// import toPairs from 'lodash/toPairs'
const COMPANY_ROOT = 'http://localhost:8080/'


export const fetchFromCompany = async ({ endpoint, method, body }) => {
  console.log("url", `${COMPANY_ROOT}${endpoint}`);
  try {
    const options = {
      method: method ? method.toUpperCase() : "GET",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(body),
    };
    console.log("options", options);
    const respObject = await fetch(`${COMPANY_ROOT}${endpoint}`, options);

    const data = await respObject.json();

    if (data.error) {
      throw data.error;
    }
    console.log("data from api", data);
    return data;
  } catch (error) {
    console.error(error);
  }
};
  
export default fetchFromCompany;
