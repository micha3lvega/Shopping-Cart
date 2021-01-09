import { Component, OnInit } from '@angular/core';

import { ProductService } from './../../services/product.service'

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products: any;

  constructor(private productService: ProductService) {
  }

  ngOnInit(): void {
    this.getAllProducts();
  }

  getAllProducts(): void {

    this.productService.getAll()
      .subscribe(
        data => {
          this.products = data;
        },
        error => {
          var retry = confirm('Error al obtener los productos, Intentar de nuevo');
          if (retry) {
            window.location.reload();
          } else {
            setTimeout(() => {                           //<<<---using ()=> syntax
              window.location.reload();
            }, 60000);
          }
        });
  }


}
