/**
 * CONFIGURACIÓN PARA SPRING BOOT
 * Adaptado a los Controllers provistos:
 * - Usuario: /api/usuario
 * - Producto: /api/producto
 * - Carrito: /api/carritos
 * - Orden: /api/ordenes
 */

// Como los archivos están en 'static', usamos ruta relativa
const API_BASE = "/api";

const endpoints = {
    // Nota: Tus controllers de Usuario y Producto usan "/CompraGamer/{id}"
    usuario: `${API_BASE}/usuario`,
    usuarioById: (id) => `${API_BASE}/usuario/CompraGamer/${id}`, 
    
    productos: `${API_BASE}/producto`,
    productoById: (id) => `${API_BASE}/producto/CompraGamer/${id}`,

    // Nota: Tus controllers usan plural para carrito y ordenes
    carrito: `${API_BASE}/carritos`, 
    carritoById: (id) => `${API_BASE}/carritos/${id}`,
    
    orden: `${API_BASE}/ordenes`,
    ordenById: (id) => `${API_BASE}/ordenes/${id}`
};

let usuarioActual = null;
let carritoState = [];
let ordenesState = [];

// Referencias al DOM
const usuarioActualEl = document.getElementById("usuario-actual");
const inputUsuarioId = document.getElementById("input-usuario-id");
const btnUsarUsuario = document.getElementById("btn-usar-usuario");
const btnCrearUsuario = document.getElementById("btn-crear-usuario");

const uNombre = document.getElementById("u-nombre");
const uApellido = document.getElementById("u-apellido");
const uEmail = document.getElementById("u-email");
const uDireccion = document.getElementById("u-direccion");

const productosContainer = document.getElementById("productos");
const countProductos = document.getElementById("count-productos");

const carritoItems = document.getElementById("carrito-items");
const carritoTotalEl = document.getElementById("carrito-total");
const btnVaciar = document.getElementById("btn-vaciar");
const btnConfirmar = document.getElementById("btn-confirmar");

const ordenesContainer = document.getElementById("ordenes");
const toastEl = document.getElementById("toast");

// --- Utilidades ---

function showToast(msg) {
    toastEl.textContent = msg;
    toastEl.classList.add("show");
    setTimeout(() => toastEl.classList.remove("show"), 3000);
}

function requiereUsuario() {
    if (!usuarioActual) {
        showToast("Primero seleccioná/creá un usuario.");
        return false;
    }
    return true;
}

function formatMoney(v) {
    const num = Number(v || 0);
    return "$ " + num.toFixed(2);
}

// --- API Helpers ---

async function handleFetchError(err, url) {
    console.error(`Error conectando a ${url}:`, err);
    showToast("Error de conexión o servidor. Revisar consola.");
    throw err;
}

async function apiGet(url) {
    try {
        const res = await fetch(url);
        if (!res.ok) throw new Error(`Error GET ${res.status}`);
        return await res.json();
    } catch (e) {
        return handleFetchError(e, url);
    }
}

async function apiPost(url, body) {
    try {
        const res = await fetch(url, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: body ? JSON.stringify(body) : null,
        });
        if (!res.ok) throw new Error(`Error POST ${res.status}`);
        return await res.json();
    } catch (e) {
        return handleFetchError(e, url);
    }
}

async function apiDelete(url) {
    try {
        const res = await fetch(url, { method: "DELETE" });
        if (!res.ok) throw new Error(`Error DELETE ${res.status}`);
        return true;
    } catch (e) {
        return handleFetchError(e, url);
    }
}

// --- Lógica de Negocio (Adaptada a tus Entidades) ---

// USUARIOS
async function crearUsuario(data) {
    // data debe coincidir con la entidad Usuario (nombre, apellido, email...)
    // Agregamos fechaRegistro automática si el backend no la pone
    data.fechaRegistro = new Date().toISOString().split('T')[0]; 
    return apiPost(endpoints.usuario, data);
}

async function obtenerUsuario(id) {
    return apiGet(endpoints.usuarioById(id));
}

// PRODUCTOS
async function obtenerProductos() {
    return apiGet(endpoints.productos);
}

// CARRITO
async function cargarCarrito() {
    if (!usuarioActual) return;
    try {
        // Tu controller devuelve TODOS los carritos de la DB.
        // Filtramos en el cliente por el usuario actual.
        const todosLosItems = await apiGet(endpoints.carrito);
        
        // Filtramos aquellos donde item.usuario.id coincida con usuarioActual.id
        carritoState = todosLosItems.filter(item => item.usuario && item.usuario.id === usuarioActual.id);
        
        renderCarrito();
    } catch (e) {
        console.error(e);
        carritoItems.innerHTML = `<div class="carrito-empty">Error al cargar carrito.</div>`;
    }
}

async function agregarAlCarrito(usuarioObj, productoObj, cantidad) {
    // ESTRUCTURA CLAVE:
    // Tu Controller espera un objeto CarritoDeCompra.
    // Como usas Entidades JPA directas, debes pasar los objetos anidados con sus IDs.
    const body = {
        cantidad: cantidad,
        usuario: { id: usuarioObj.id },   // Referencia al usuario
        producto: { id: productoObj.id }  // Referencia al producto
    };
    return apiPost(endpoints.carrito, body);
}

async function eliminarItemCarrito(itemId) {
    return apiDelete(endpoints.carritoById(itemId));
}

async function vaciarCarritoLocal() {
    // Como tu backend no tiene "borrar todo por usuario", lo hacemos uno por uno.
    if(carritoState.length === 0) return;
    
    const promesas = carritoState.map(item => eliminarItemCarrito(item.id));
    await Promise.all(promesas);
}

// ORDENES
async function cargarOrdenes() {
    if (!usuarioActual) return;
    try {
        // Tu controller devuelve TODAS las ordenes. Filtramos en cliente.
        const todasLasOrdenes = await apiGet(endpoints.orden);
        ordenesState = todasLasOrdenes.filter(o => o.usuario && o.usuario.id === usuarioActual.id);
        renderOrdenes();
    } catch (e) {
        console.error(e);
        ordenesContainer.innerHTML = `<div class="carrito-empty">Error al cargar órdenes.</div>`;
    }
}

async function crearOrden(totalCalculado) {
    // Construimos la Orden según tu Entidad
    const ordenData = {
        fechaCreacion: new Date().toISOString(), // LocalDateTime aproximado
        total: totalCalculado,
        estado: "CONFIRMADA",
        usuario: { id: usuarioActual.id } // Relación con usuario
        // Nota: No enviamos la lista de items porque tu lógica no los vincula automáticamente al crear
    };
    return apiPost(endpoints.orden, ordenData);
}


// --- Renderizado ---

function setUsuarioActual(usuario) {
    usuarioActual = usuario;
    if (!usuario) {
        usuarioActualEl.textContent = "(ninguno)";
        btnConfirmar.disabled = true;
        carritoItems.innerHTML = `<div class="carrito-empty">Seleccioná un usuario para usar el carrito.</div>`;
        ordenesContainer.innerHTML = `<div class="carrito-empty">Seleccioná un usuario para ver sus órdenes.</div>`;
        carritoTotalEl.textContent = formatMoney(0);
        return;
    }
    usuarioActualEl.textContent = `${usuario.id} · ${usuario.nombre} ${usuario.apellido}`;
    btnConfirmar.disabled = false;
}

function renderProductos(productos) {
    productosContainer.innerHTML = "";
    countProductos.textContent = `${productos.length} producto${productos.length !== 1 ? "s" : ""}`;

    if (!productos || productos.length === 0) {
        productosContainer.innerHTML = `<div class="carrito-empty">No hay productos disponibles.</div>`;
        return;
    }

    productos.forEach((p) => {
        const card = document.createElement("div");
        card.className = "producto-card";

        const info = document.createElement("div");
        info.className = "producto-info";
        info.innerHTML = `
            <h3>${p.nombre}</h3>
            <p>${p.descripcion || ""}</p>
        `;

        const precio = document.createElement("div");
        precio.className = "producto-precio";
        precio.textContent = formatMoney(p.precio);

        const actions = document.createElement("div");
        actions.className = "producto-actions";

        const input = document.createElement("input");
        input.type = "number";
        input.min = "1";
        input.value = "1";
        input.className = "input-cantidad";

        const btn = document.createElement("button");
        btn.className = "btn-primary";
        btn.textContent = "Agregar";

        btn.addEventListener("click", async () => {
            if (!requiereUsuario()) return;
            const cantidad = parseInt(input.value, 10) || 1;

            try {
                await agregarAlCarrito(usuarioActual, p, cantidad);
                showToast(`Agregado: ${p.nombre}`);
                await cargarCarrito();
            } catch (e) {
                console.error(e);
            }
        });

        actions.appendChild(input);
        actions.appendChild(btn);
        card.appendChild(info);
        card.appendChild(precio);
        card.appendChild(actions);

        productosContainer.appendChild(card);
    });
}

function renderCarrito() {
    carritoItems.innerHTML = "";

    if (!carritoState || carritoState.length === 0) {
        carritoItems.innerHTML = `<div class="carrito-empty">Tu carrito está vacío.</div>`;
        carritoTotalEl.textContent = formatMoney(0);
        return;
    }

    let total = 0;

    carritoState.forEach((item) => {
        // item es la entidad CarritoDeCompra
        const prod = item.producto; 
        if(!prod) return; // Protección por si viene null

        const precioUnit = prod.precio || 0;
        const subtotalVal = precioUnit * item.cantidad;
        total += subtotalVal;

        const row = document.createElement("div");
        row.className = "carrito-row";

        const nombreDiv = document.createElement("div");
        nombreDiv.textContent = prod.nombre || "Producto";

        const cantDiv = document.createElement("div");
        cantDiv.textContent = `x${item.cantidad}`;

        const subtotalDiv = document.createElement("div");
        subtotalDiv.textContent = formatMoney(subtotalVal);

        const acciones = document.createElement("div");
        acciones.style.display = "flex";
        acciones.style.justifyContent = "flex-end";

        const btn = document.createElement("button");
        btn.className = "btn-danger-outline";
        btn.textContent = "✕";
        btn.title = "Eliminar";
        btn.addEventListener("click", async () => {
            try {
                await eliminarItemCarrito(item.id);
                await cargarCarrito();
                showToast("Item eliminado");
            } catch (e) { console.error(e); }
        });

        acciones.appendChild(btn);
        row.appendChild(nombreDiv);
        row.appendChild(cantDiv);
        row.appendChild(subtotalDiv);
        row.appendChild(acciones);

        carritoItems.appendChild(row);
    });

    carritoTotalEl.textContent = formatMoney(total);
    // Guardamos el total globalmente para usarlo al confirmar
    carritoTotalEl.dataset.value = total;
}

function renderOrdenes() {
    ordenesContainer.innerHTML = "";

    if (!ordenesState || ordenesState.length === 0) {
        ordenesContainer.innerHTML = `<div class="carrito-empty">Sin historial.</div>`;
        return;
    }

    ordenesState.forEach((o) => {
        const row = document.createElement("div");
        row.className = "orden-row";

        const left = document.createElement("div");
        const fecha = o.fechaCreacion ? new Date(o.fechaCreacion).toLocaleDateString() : "-";
        
        left.innerHTML = `
            <div class="orden-id">Orden #${o.id} <span style="font-weight:normal; font-size:0.8em">(${o.estado})</span></div>
            <div class="orden-meta">${fecha}</div>
        `;

        const total = document.createElement("div");
        total.className = "orden-total";
        total.textContent = formatMoney(o.total || 0);

        row.appendChild(left);
        row.appendChild(total);
        ordenesContainer.appendChild(row);
    });
}


// --- Event Listeners ---

btnUsarUsuario.addEventListener("click", async () => {
    const id = parseInt(inputUsuarioId.value, 10);
    if (!id) {
        showToast("Ingresá un ID válido.");
        return;
    }
    try {
        // Nota: getUsuarioById devuelve un Optional. Si es null o vacío, manejarlo.
        const usuario = await obtenerUsuario(id);
        
        // Si tu backend devuelve null cuando no encuentra, usuario será null.
        if (!usuario) {
            showToast("Usuario no encontrado.");
            return;
        }
        
        setUsuarioActual(usuario);
        showToast(`Hola, ${usuario.nombre}`);
        await cargarCarrito();
        await cargarOrdenes();
    } catch (e) {
        console.error(e);
        showToast("Error al buscar usuario.");
    }
});

btnCrearUsuario.addEventListener("click", async () => {
    const data = {
        nombre: uNombre.value || "Nombre",
        apellido: uApellido.value || "Apellido",
        email: uEmail.value || "mail@test.com",
        direccion: uDireccion.value || "Calle Falsa 123"
    };

    try {
        const nuevo = await crearUsuario(data);
        setUsuarioActual(nuevo);
        showToast(`Usuario #${nuevo.id} creado.`);
        inputUsuarioId.value = nuevo.id;
        await cargarCarrito();
        await cargarOrdenes();
    } catch (e) {
        console.error(e);
        showToast("Error al crear usuario.");
    }
});

btnVaciar.addEventListener("click", async () => {
    if (!requiereUsuario()) return;
    if (carritoState.length === 0) return;
    if (!confirm("¿Vaciar carrito?")) return;

    try {
        await vaciarCarritoLocal();
        await cargarCarrito();
        showToast("Carrito vaciado");
    } catch (e) {
        console.error(e);
        showToast("Error al vaciar.");
    }
});

btnConfirmar.addEventListener("click", async () => {
    if (!requiereUsuario()) return;
    if (carritoState.length === 0) {
        showToast("El carrito está vacío.");
        return;
    }
    
    if (!confirm("¿Confirmar compra?")) return;

    try {
        const total = parseFloat(carritoTotalEl.dataset.value || 0);
        
        // 1. Crear la orden
        const orden = await crearOrden(total);
        showToast(`Orden #${orden.id} generada.`);
        
        // 2. Vaciar el carrito (simulando que se procesó)
        await vaciarCarritoLocal();
        
        // 3. Recargar
        await cargarCarrito();
        await cargarOrdenes();
        
    } catch (e) {
        console.error(e);
        showToast("Error al procesar compra.");
    }
});

// --- Init ---
(async function init() {
    console.log("App iniciada. API: ", API_BASE);
    setUsuarioActual(null);
    await obtenerProductos().then(renderProductos).catch(e => {
        console.error(e);
        productosContainer.innerHTML = `<div class="carrito-empty" style="color:red">Error de conexión con Backend</div>`;
    });
})();