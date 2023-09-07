
function addSales(){
    const idFact = document.getElementById("idFact").value;
    if(idFact ==""){
        alert("No se puede usar esa ID")
    }else{
        document.getElementById("idClient").removeAttribute("disabled");
        document.getElementById("idProduct").removeAttribute("disabled");
        document.getElementById("cant").removeAttribute("disabled");
        document.getElementById("btnSave").removeAttribute("disabled");
        document.getElementById("btnCancel").removeAttribute("disabled");

        document.getElementById("btnAdd").setAttribute("disabled","btnAdd");
        document.getElementById("btnSearch").setAttribute("disabled","btnSearch");
        document.getElementById("idFact").setAttribute("disabled","idFact");
    }
}

function agregar_venta()
{
    const idFact = document.getElementById("idFact").value;
    const idProduct = document.getElementById("idProduct").value;
    const idClient = document.getElementById("idClient").value;
    const cant = document.getElementById("cant").value;

    if(idProduct == "" || idFact== "" || idClient== "" || cant== ""){
        alert("No se puede guardar campos vacíos")
    }else{
         const varSend = `codigo=${idFact}&id_cliente=${idClient}&cod_producto=${idProduct}&cantidad=${cant}`;
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "control_venta", true);
        xhr.onreadystatechange = () => {
            if (xhr.readyState == 4 && xhr.status == 200) {
                alert(xhr.responseText)
                cancelSale();
               // leer_venta();
            }
        };
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhr.send(varSend);
    }
}

function cancelSale() {
    document.getElementById("idProduct").value = "";
    document.getElementById("idFact").value = "";
    document.getElementById("idClient").value = "";
    document.getElementById("cant").value = "";

    document.getElementById("idFact").removeAttribute("disabled");
    document.getElementById("btnAdd").removeAttribute("disabled");
    document.getElementById("btnSearch").removeAttribute("disabled");

    document.getElementById("idProduct").setAttribute("disabled","idProduct");
    document.getElementById("idClient").setAttribute("disabled","idClient");
    document.getElementById("cant").setAttribute("disabled","cant");
    document.getElementById("btnSave").setAttribute("disabled","btnSave");
    document.getElementById("btnCancel").setAttribute("disabled","btnCancel");
}

function onlyNums(e){
    const code = window.event ? e.which : e.keyCode;
    return !( code < 48 || code > 57 );
}

function onlyAlpha(e){
    const code = window.event ? e.which : e.keyCode;
    return !(code != 32 && (code < 65 || code > 90) && (code < 97 || code > 122));
}

function borrar_venta()
{
    const id = document.getElementById('id_borrar').value;
   
    if(confirm('¿Desea Continuar?')){
        var xhr = new XMLHttpRequest();
        xhr.open('GET',"control_cliente?option=1&id="+id,true);
        xhr.onreadystatechange = () => {
            if (xhr.readyState == 4 && xhr.status == 200) {
                alert(xhr.responseText);
                leer_clientes();
            }
        }   
        xhr.send(null);
    }
}


function consultar_venta()
{
     const idFact = document.getElementById("idFact").value;
    if (idFact==""){
        alert("No se puede buscar un campo vacío")
    }else{
         var xhr = new XMLHttpRequest();
        xhr.open('GET',"control_venta?option=2&id="+idFact,true);
        xhr.onreadystatechange = () => {
            if (xhr.readyState == 4 && xhr.status == 200) {
                alert(xhr.responseText);
                
            }
        }   
    xhr.send(null);
    }
   
}
