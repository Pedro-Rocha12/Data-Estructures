int avlNODES(int altura){
    if(altura == 0){
        return 1;
    }else if(altura == 1){
        return 2;
    }else{
        return (1 + avlNODES(altura - 1) + avlNODES(altura -2));
    }
}