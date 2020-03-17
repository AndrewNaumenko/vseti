export interface OfferModel {
  id: number;
  title: string;
  description: string;
  price: number;
  photo: string;
  category: {category: string, id: number};
}
