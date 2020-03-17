export interface CategoryModel {
  id: number;
  category: string;
  offers: {id: number, title: string, description: string, price: number, photo: string}[];
}
