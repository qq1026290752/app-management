export interface PageHelper<T>{

  records:Array<T>;
  total:number;
  current:number;
  pages:number;
  size:number;
}
