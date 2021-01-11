import { Component, OnInit } from '@angular/core';

// Example https://www.itsolutionstuff.com/post/angular-9-8-sweetalert2-sample-example-tutorialexample.html
import Swal from 'sweetalert2/dist/sweetalert2.js';

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
          console.error('[ProductListComponent] (getAllProducts) error: ', error);
          this.onErrorGetProducts();
        });
  }


  onErrorGetProducts(): void {
    Swal.fire({
      title: 'Error al obtener los productos',
      text: '¿Intentar de nuevo?',
      icon: 'error',
      showCancelButton: true,
      confirmButtonText: 'Si',
      cancelButtonText: 'No'
    }).then((result) => {
      if (result.value) {
        window.location.reload();
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        setTimeout(() => {
          window.location.reload();
        }, 60000);
      }
    })
  }

}
