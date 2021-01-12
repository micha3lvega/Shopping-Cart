import { Brand } from "./brand";
import { Category } from "./category";
import { Image } from "./image";

export interface Product {

  id: string;
  sku: number;
  name: string;
  description: string;
  state: string;
  price: number;
  stock: number;
  image: Image;
  brand: Brand;
  category: Category;
  minStock: number;
  createDate: Date;
  lastUpdate: Date;

}
