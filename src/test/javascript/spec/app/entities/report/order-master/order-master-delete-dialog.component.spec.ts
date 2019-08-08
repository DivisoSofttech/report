/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ReportTestModule } from '../../../../test.module';
import { OrderMasterDeleteDialogComponent } from 'app/entities/report/order-master/order-master-delete-dialog.component';
import { OrderMasterService } from 'app/entities/report/order-master/order-master.service';

describe('Component Tests', () => {
    describe('OrderMaster Management Delete Component', () => {
        let comp: OrderMasterDeleteDialogComponent;
        let fixture: ComponentFixture<OrderMasterDeleteDialogComponent>;
        let service: OrderMasterService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ReportTestModule],
                declarations: [OrderMasterDeleteDialogComponent]
            })
                .overrideTemplate(OrderMasterDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(OrderMasterDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OrderMasterService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
