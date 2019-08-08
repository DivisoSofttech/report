import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOrderMaster } from 'app/shared/model/report/order-master.model';

@Component({
    selector: 'jhi-order-master-detail',
    templateUrl: './order-master-detail.component.html'
})
export class OrderMasterDetailComponent implements OnInit {
    orderMaster: IOrderMaster;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ orderMaster }) => {
            this.orderMaster = orderMaster;
        });
    }

    previousState() {
        window.history.back();
    }
}
