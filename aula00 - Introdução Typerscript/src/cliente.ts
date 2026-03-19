import Endereco from "./endereco";
import Telefone from "./telefone";

export default class Cliente {
    private _nome : string;
    private _cpf : number;
    private _data_nascimento : number;
    private _sexo : string;
    private _endereco : Endereco;
    private _telefones : Telefone[];

    constructor(nome : string, cpf : number, data_nascimento : number, sexo : string, endereco : Endereco, telefones : Telefone[]) {
        this._nome = nome;
        this._cpf = cpf;
        this._data_nascimento = data_nascimento;
        this._sexo = sexo;
        this._endereco = endereco;
        this._telefones = telefones;
    }

        public get nome(): string {
        return this._nome;
    }

    get cpf(): number {
        return this._cpf;
    }

    get data_nascimento(): number {
        return this._data_nascimento;
    }

    get sexo(): string {
        return this._sexo;
    }

    get endereco(): Endereco {
        return this._endereco;
    }

    get telefones(): Telefone[] {
        return this._telefones;
    }

    set nome(valor: string) {
        this._nome = valor;
    }

    set cpf(valor: number) {
        this._cpf = valor;
    }

    set data_nascimento(valor: number) {
        this._data_nascimento = valor;
    }

    set sexo(valor: string) {
        this._sexo = valor;
    }

    set endereco(valor: Endereco) {
        this._endereco = valor;
    }

    set telefones(valor: Telefone[]) {
        this._telefones = valor;
    }
}