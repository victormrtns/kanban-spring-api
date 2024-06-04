export interface ILogin {
    email: string;
    password: string;
  }
  
  export interface IRegister extends ILogin {
    nome: string
  }
  
  export interface ILoginReponse {
    nome: string;
    token: string;
  }
  
  export interface IUser {
    id: number;
    email: string;
    primeiroNome: string;
    ultimoNome: string;
  }