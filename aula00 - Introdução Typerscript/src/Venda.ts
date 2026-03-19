import Cliente from "./cliente";
import Produto from "./produto";

class Venda{
    private _codigo : number;
    private _data : number;
    private _cliente : Cliente;
    private _produtos : Produto[];

    constructor(codigo : number, data : number, cliente : Cliente, produtos : Produto[]) {
        this._codigo = codigo;
        this._data = data;
        this._cliente = cliente;
        this._produtos = produtos;
    }

    calculaTotal() : number{
        let total = 0;
        for(let produto of this._produtos){
            total+=produto.valor;
        }
        return total;
    }
    get codigo(): number {
        return this._codigo;
    }

    get data(): number {
        return this._data;
    }

    get cliente(): Cliente {
        return this._cliente;
    }

    get produtos(): Produto[] {
        return this._produtos;
    }

    set codigo(valor: number) {
        this._codigo = valor;
    }

    set data(valor: number) {
        this._data = valor;
    }

    set cliente(valor: Cliente) {
        this._cliente = valor;
    }

    set produtos(valor: Produto[]) {
        this._produtos = valor;
    }
}