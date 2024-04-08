//set ticketarray and the area where they are displayed
let ticketarr = [];
let ticketView = document.querySelector(".billetter");
document.querySelector("#deleteall").addEventListener("click", emptyTickets);

let tlfRegEx = new RegExp(/^[0-9]{8}$/);
let epostRegEx = new RegExp(/[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}/)


//set to global scope
let filmvalg;
let antall;
let fornavn;
let etternavn;
let telefonnr;
let epost;
let valElements = [];

//could be loopified, but tables are a pain to work with
const valgErr = document.querySelector("#valgErr");
const antallErr = document.querySelector(".antallRad td:nth-child(3)");
const fnavnErr = document.querySelector(".fornavnRad td:nth-child(3)");
const enavnErr = document.querySelector(".etternavnRad td:nth-child(3)");
const tlfErr = document.querySelector(".telefonRad td:nth-child(3)");
const epostErr = document.querySelector(".epostRad td:nth-child(3)");
const errElements = [valgErr, antallErr, fnavnErr, enavnErr, tlfErr, epostErr];

document.querySelector("#purchase").addEventListener("click", purchase);


function validateInput() {
    //empty list
    errorlist = [];
    //picked a movie?
    if (filmvalg === "") errorlist.push("valg");
    //attempt to turn user input into a number - and catch exception if they put something else
    try {
        antall = Number(antall);
    } catch (error) {
        errorlist.push("antall");
    }
    //check if it is positive
    if (antall < 1) errorlist.push("antall");
    //check for empty strings
    if (fornavn === "") errorlist.push("fornavn");
    if (etternavn === "") errorlist.push("etternavn");

    //check is the phonenumber is 8 digits (norwegian number)
    if (!tlfRegEx.test(telefonnr)) errorlist.push("telefonnr");
    //email must atleast contain an @
    if (!epostRegEx.test(epost)) errorlist.push("epost");
    return errorlist;
}

function emptyTickets() {
    $.post("/delete")
    updateView();
}

function clearErrors() {
    errElements.forEach((item) => {
        item.innerHTML = "";
    });
}

function showErrors(errorlist) {
    clearErrors();
    errorlist.forEach((item) => {
        switch (item) {
            case 'valg':
                valgErr.innerHTML = "Velg en film";
                break;
            case 'antall':
                antallErr.innerHTML = "Velg et tall over 1";
                break;
            case 'fornavn':
                fnavnErr.innerHTML = "Felt kan ikke vært tomt";
                break;
            case 'etternavn':
                enavnErr.innerHTML = "Felt kan ikke være tomt";
                break;
            case 'telefonnr':
                tlfErr.innerHTML = "Skriv inn et riktig nummer (8 siffer)";
                break;
            case 'epost':
                epostErr.innerHTML = "Skriv inn en e-post adresse";
                break;
            default:
                alert("intern feil");
        }
    });
}


function updateView() {
    ticketarr = [];
    $.getJSON("/getList", (data) => {
            ticketarr = data;
        }
    ).done(() => {
        let out = "";
        out += "\n" +
            "    <tr class='bolder'>\n" +
            "        <td>Film</td>\n" +
            "        <td>Antall</td>\n" +
            "        <td>Fornavn</td>\n" +
            "        <td>Etternavn</td>\n" +
            "        <td>Telefonnummer</td>\n" +
            "        <td>E-Post</td>\n" +
            "    </tr>";
        //debugger;

        //Normal array iteration did not work, but this did. basically a function that does a for loop through the array
        //with arrowfunctions
        ticketarr.forEach((item) => {
            out += "<tr>";
            console.log(item);
            Object.values(item).forEach((value) => {
                out += "<td>" + value + "</td>";
            });
            out += "</tr>";
        })

        ticketView.innerHTML = out


    });
    }

function addTicket() {
    let requestData = {
        movieName: filmvalg,
        amount: antall,
        firstName: fornavn,
        lastName: etternavn,
        phoneNumber: telefonnr,
        eMail: epost,
    };
    console.log(JSON.stringify(requestData));

    $.ajax({
        type: "POST",
        url: "/addTicket", // Change this URL to match your endpoint
        contentType: "application/json",
        data: JSON.stringify(requestData),
        success: function (response) {
            console.log("Success:", response);
            // Handle success response here
        },
        error: function (xhr, status, error) {
            console.error("Error:", error);
            // Handle error here
        }
        //wait for callback
    }).done(() => {
        updateView();
    });



}

function purchase() {
    //get values each buy-event, to avoid values being stored across purchases
    filmvalg = document.getElementById("valg").value;
    antall = String(document.getElementById("antall").value);
    fornavn = document.getElementById("fornavn").value;
    etternavn = document.getElementById("etternavn").value;
    telefonnr = document.getElementById("telefonnr").value;
    epost = document.getElementById("epost").value;
    //update the array with the new values (somehow this needs to be here)
    valElements = [filmvalg, antall, fornavn, etternavn, telefonnr, epost];
    //get the errors
    let errors = validateInput();

    //if no errors, go ahead
    if (errors.length === 0) {
        clearErrors();
        addTicket();
        //alert("Suksess"); // alert to give the server some time to respond
    } else {
        clearErrors();
        showErrors(errors);
        updateView();
    }

}

//når all koden har kjørt så laster vi tabellen (tilfelle man bruker cookies for å lagre/hente arrayen senere)
//updateView();