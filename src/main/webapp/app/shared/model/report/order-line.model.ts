export interface IOrderLine {
    id?: number;
    item?: string;
    quantity?: number;
    total?: number;
    orderMasterId?: number;
}

export class OrderLine implements IOrderLine {
    constructor(public id?: number, public item?: string, public quantity?: number, public total?: number, public orderMasterId?: number) {}
}
