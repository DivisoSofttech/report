import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOrderMaster } from 'app/shared/model/report/order-master.model';
import { OrderMasterService } from './order-master.service';

@Component({
    selector: 'jhi-order-master-delete-dialog',
    templateUrl: './order-master-delete-dialog.component.html'
})
export class OrderMasterDeleteDialogComponent {
    orderMaster: IOrderMaster;

    constructor(
        private orderMasterService: OrderMasterService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.orderMasterService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'orderMasterListModification',
                content: 'Deleted an orderMaster'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-order-master-delete-popup',
    template: ''
})
export class OrderMasterDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ orderMaster }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(OrderMasterDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.orderMaster = orderMaster;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
