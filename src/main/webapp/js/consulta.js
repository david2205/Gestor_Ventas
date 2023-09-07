function viewTable() {
    const option = document.getElementById("option").value;

    if (option == "Clientes Registrados"){
        alert(option);
        clientes();
        document.getElementById("tblClient").style.display = 'block';
        document.getElementById("tblProd").style.display = 'none';
        document.getElementById("tblVenta").style.display = 'none';
    }else if (option == "Productos Registrados"){
        alert(option);
        productos();
        document.getElementById("tblProd").style.display = 'block';
        document.getElementById("tblClient").style.display = 'none';
        document.getElementById("tblVenta").style.display = 'none';
    }else if (option == "Ventas Registradas"){
        alert(option);
        ventas();
        document.getElementById("tblVenta").style.display = 'block';
        document.getElementById("tblClient").style.display = 'none';
        document.getElementById("tblProd").style.display = 'none';
    }else{
        alert("Escoga su opción")
        document.getElementById("tblVenta").style.display = 'none';
        document.getElementById("tblClient").style.display = 'none';
        document.getElementById("tblProd").style.display = 'none';
    }
}

function clientes(){
    const table_data = document.getElementById('tableClient');
    table_data.innerHTML="";
    var xhr = new XMLHttpRequest();
    xhr.open("GET","control_cliente?option=0",true);
    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4 && xhr.status == 200) {
            
            var clientes = JSON.parse(xhr.responseText);
            for ( cliente of clientes)
            {
               
                var ROW = document.createElement("tr");
                var COL = document.createElement("td");
                COL.appendChild(document.createTextNode(cliente.id));
                ROW.appendChild(COL);
                var COL = document.createElement("td");
                COL.appendChild(document.createTextNode(cliente.Nombres));
                ROW.appendChild(COL);
                var COL = document.createElement("td");
                COL.appendChild(document.createTextNode(cliente.Apellidos));
                ROW.appendChild(COL);
                var COL = document.createElement("td");
                COL.appendChild(document.createTextNode(cliente.Contacto));
                ROW.appendChild(COL);
                var COL = document.createElement("td");
                COL.appendChild(document.createTextNode(cliente.Edad));
                ROW.appendChild(COL);
                table_data.appendChild(ROW);
            }
        }
    };
    xhr.send(null);
};

function productos(){
    const table_data = document.getElementById('tableProduct');
    table_data.innerHTML="";
    var xhr = new XMLHttpRequest();
    xhr.open("GET","control_producto?option=0",true);
    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4 && xhr.status == 200) {
            
            var productos = JSON.parse(xhr.responseText);
            for ( producto of productos)
            {
               
                var ROW = document.createElement("tr");
                var COL = document.createElement("td");
                COL.appendChild(document.createTextNode(producto.Codigo));
                ROW.appendChild(COL);
                var COL = document.createElement("td");
                COL.appendChild(document.createTextNode(producto.Nombre));
                ROW.appendChild(COL);
                var COL = document.createElement("td");
                COL.appendChild(document.createTextNode(producto.Valor));
                ROW.appendChild(COL);
                var COL = document.createElement("td");
                COL.appendChild(document.createTextNode(producto.Fecha));
                ROW.appendChild(COL);
                
                
                table_data.appendChild(ROW);
            }
        }
    };
    xhr.send(null);
};

function ventas(){
    const table_data = document.getElementById('tableVentas');
    table_data.innerHTML="";
    var xhr = new XMLHttpRequest();
    xhr.open("GET","control_venta?option=0",true);
    xhr.onreadystatechange = () => {
        if (xhr.readyState == 4 && xhr.status == 200) {
            
            var ventas = JSON.parse(xhr.responseText);
            console.log(ventas);
            for ( venta of ventas)
            {
               
                var ROW = document.createElement("tr");
                var COL = document.createElement("td");
                COL.appendChild(document.createTextNode(venta.Codigo));
                ROW.appendChild(COL);
                var COL = document.createElement("td");
                COL.appendChild(document.createTextNode(venta.id_cliente));
                ROW.appendChild(COL);
                var COL = document.createElement("td");
                COL.appendChild(document.createTextNode(venta.cod_producto));
                ROW.appendChild(COL);
                var COL = document.createElement("td");
                COL.appendChild(document.createTextNode(venta.Cantidad));
                ROW.appendChild(COL);
                var COL = document.createElement("td");
                COL.appendChild(document.createTextNode(venta.total));
                ROW.appendChild(COL);
                table_data.appendChild(ROW);
            }
        }
    };
    xhr.send(null);
};