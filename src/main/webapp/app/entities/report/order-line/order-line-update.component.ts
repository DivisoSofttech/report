import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IOrderLine } from 'app/shared/model/report/order-line.model';
import { OrderLineService } from './order-line.service';
import { IOrderMaster } from 'app/shared/model/report/order-master.model';
import { OrderMasterService } from 'app/entities/report/order-master';

@Component({
    selector: 'jhi-order-line-update',
    templateUrl: './order-line-update.component.html'
})
export class OrderLineUpdateComponent implements OnInit {
    orderLine: IOrderLine;
    isSaving: boolean;

    ordermasters: IOrderMaster[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private orderLineService: OrderLineService,
        private orderMasterService: OrderMasterService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ orderLine }) => {
            this.orderLine = orderLine;
        });
        this.orderMasterService.query().subscribe(
            (res: HttpResponse<IOrderMaster[]>) => {
                this.ordermasters = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.orderLine.id !== undefined) {
            this.subscribeToSaveResponse(this.orderLineService.update(this.orderLine));
        } else {
            this.subscribeToSaveResponse(this.orderLineService.create(this.orderLine));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IOrderLine>>) {
        result.subscribe((res: HttpResponse<IOrderLine>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackOrderMasterById(index: number, item: IOrderMaster) {
        return item.id;
    }
}
