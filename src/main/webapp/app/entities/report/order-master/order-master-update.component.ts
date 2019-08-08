import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IOrderMaster } from 'app/shared/model/report/order-master.model';
import { OrderMasterService } from './order-master.service';

@Component({
    selector: 'jhi-order-master-update',
    templateUrl: './order-master-update.component.html'
})
export class OrderMasterUpdateComponent implements OnInit {
    orderMaster: IOrderMaster;
    isSaving: boolean;

    constructor(private orderMasterService: OrderMasterService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ orderMaster }) => {
            this.orderMaster = orderMaster;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.orderMaster.id !== undefined) {
            this.subscribeToSaveResponse(this.orderMasterService.update(this.orderMaster));
        } else {
            this.subscribeToSaveResponse(this.orderMasterService.create(this.orderMaster));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IOrderMaster>>) {
        result.subscribe((res: HttpResponse<IOrderMaster>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
