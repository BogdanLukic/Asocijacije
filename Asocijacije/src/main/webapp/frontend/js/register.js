img_count = 0;
function carousel_right(){
    img_count++;
    if(img_count >= 8)
        img_count = 0;
    img_character=document.querySelector("#img-character");
    img_character.src = `../images/characters/${img_count}.png`
}
function carousel_left(){
    img_count--;
    if(img_count <= -1)
        img_count = 7;
    img_character=document.querySelector("#img-character");
    img_character.src = `../images/characters/${img_count}.png`
}