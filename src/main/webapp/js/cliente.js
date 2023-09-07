
function agregar_cliente()
{
    const idClient = document.getElementById("idClient").value;
    const names = document.getElementById("names").value;
    const lastName = document.getElementById("lastName").value;
    const contacto = document.getElementById("contacto").value;
    const edad = document.getElementById("edad").value;
    
     if(idClient == "" || names== "" || lastName == "" || contacto == "" || edad == ""){
        alert("No se puede guardar campos vacíos")
    }else{
        
        const varSend = `id=${idClient}&nombres=${names}&apellidos=${lastName}&contacto=${contacto}&edad=${edad}`;
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "control_cliente", true);
        xhr.onreadystatechange = () => {
            if (xhr.readyState == 4 && xhr.status == 200) {
                if (!JSON.parse(xhr.responseText)) {
                    alert("Error La id del Cliente ya ha sido Registrada");
                } else {
                   alert("Cliente Registrado con éxito");
                   cancelClient();
                }
            }
        };
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhr.send(varSend);
        
    }
    
}


function cancelClient() {
    document.getElementById("lastName").value = "";
    document.getElementById("names").value = "";
    document.getElementById("idClient").value = "";
    document.getElementById("contacto").value = "";
    document.getElementById("edad").value = "";

    document.getElementById("idClient").removeAttribute("disabled");
    document.getElementById("btnAdd").removeAttribute("disabled");
    document.getElementById("btnSearch").removeAttribute("disabled");
    document.getElementById("btnDelete").removeAttribute("disabled");

    document.getElementById("lastName").setAttribute("disabled","lastName");
    document.getElementById("names").setAttribute("disabled","names");
    document.getElementById("btnSave").setAttribute("disabled","btnSave");
    document.getElementById("btnCancel").setAttribute("disabled","btnCancel");
    document.getElementById("contacto").setAttribute("disabled","contacto");
    document.getElementById("edad").setAttribute("disabled","edad");
}

function onlyNums(e){
    const code = window.event ? e.which : e.keyCode;
    return !( code < 48 || code > 57 );
}

function onlyAlpha(e){
    const code = window.event ? e.which : e.keyCode;
    return !(code != 32 && (code < 65 || code > 90) && (code < 97 || code > 122));
}

function addClient(){
    const idClient = document.getElementById("idClient").value;
    if(idClient ==""){
        alert("No se puede usar esa ID")
    }else{
        document.getElementById("lastName").removeAttribute("disabled");
        document.getElementById("names").removeAttribute("disabled");
        document.getElementById("contacto").removeAttribute("disabled");
        document.getElementById("edad").removeAttribute("disabled");
        document.getElementById("btnSave").removeAttribute("disabled");
        document.getElementById("btnCancel").removeAttribute("disabled");

        document.getElementById("btnAdd").setAttribute("disabled","btnAdd");
        document.getElementById("btnDelete").setAttribute("disabled","btnDelete");
        document.getElementById("btnSearch").setAttribute("disabled","btnSearch");
        document.getElementById("idClient").setAttribute("disabled","idClient");
    }
}

function borrar_cliente()
{
    
    const idClient = document.getElementById("idClient").value;
    if (idClient==""){
        alert("No se puede eliminar un campo vacío")
    }else{
        alert(idClient)
        var xhr = new XMLHttpRequest();
        xhr.open('GET',"control_cliente?option=1&id="+idClient,true);
        xhr.onreadystatechange = () => {
            if (xhr.readyState == 4 && xhr.status == 200) {
                alert(xhr.responseText);
               // leer_clientes();
            }
        }   
        xhr.send(null);
    }
    
}


function consultar_cliente()
{
    
     const idClient = document.getElementById("idClient").value;
    if (idClient==""){
        alert("No se puede buscar un campo vacío")
    }else{
        alert(idClient);
        var xhr = new XMLHttpRequest();
        xhr.open('GET',"control_cliente?option=2&id="+idClient,true);
        xhr.onreadystatechange = () => {
            if (xhr.readyState == 4 && xhr.status == 200) {
                alert(xhr.responseText);
                
            }
        }   
    xhr.send(null);
    }    
}
