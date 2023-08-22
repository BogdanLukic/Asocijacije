function initAdmin(){
    getConnection();
    registerAdmin();
    getAllUser();
    subscribing();
    getAllAssociations();
}

// EMITS
// ===========================================================================

function registerAdmin(){
    socket.emit("admin-register","");
}
function getAllUser(){
    socket.emit("get-user-list","");
}
// EVENTS
// ===========================================================================
function subscribing(){
    reciveAllUser();
}
function reciveAllUser(){
    socket.on('get-user-list',(data)=>{
        setListOfUser(data);
    })
}

// Fetch
// ===========================================================================
function getAllAssociations(){
    let options = {
        method: 'GET',
        headers: {
            'Content-Type': 
                'application/json;charset=utf-8'
        }  
    }
    let fetchRes = fetch("http://localhost:8081/Asocijacije_war_exploded/associationlist",options);
    fetchRes.then(res => res.json())
    .then(
        d=>{
            renderAssociations(d);
        }
    )
}
